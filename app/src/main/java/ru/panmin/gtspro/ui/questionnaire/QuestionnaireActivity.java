package ru.panmin.gtspro.ui.questionnaire;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.DialogUtils;
import ru.panmin.gtspro.utils.OtherUtils;
import ru.panmin.gtspro.utils.SpaceItemDecoration;

public class QuestionnaireActivity
        extends ToolbarActivity
        implements QuestionnaireMvpView,
        QuestionnaireAdapter.AnswerQuestionListener {

    private static final int REQ_SETTINGS = 666;
    private static final String INTENT_KEY_QUESTION_IDS = "question.ids";
    private final RxPermissions rxPermissions = new RxPermissions(this);
    @Inject QuestionnairePresenter questionnairePresenter;
    @Inject QuestionnaireAdapter questionnaireAdapter;
    @BindView(R.id.recyclerViewQuestionnaire) RecyclerView recyclerViewQuestionnaire;
    private QuestionnaireAdapter.AddNewPhotoListener addNewPhotoListener;
    private CameraImagePicker cameraImagePicker;
    private String outputPath;

    public QuestionnaireActivity() {
    }

    public static Intent getStartIntent(Context context, List<String> questionIds) {
        Intent intent = new Intent(context, QuestionnaireActivity.class);
        intent.putStringArrayListExtra(INTENT_KEY_QUESTION_IDS, new ArrayList<>(questionIds));
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("picker_path", outputPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("picker_path")) {
                outputPath = savedInstanceState.getString("picker_path");
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Picker.PICK_IMAGE_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (cameraImagePicker != null) {
                        cameraImagePicker.submit(data);
                    }
                }
                break;
            case REQ_SETTINGS:
                pickImage(addNewPhotoListener);
                addNewPhotoListener = null;
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_questionnaire;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        questionnairePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        cameraImagePicker = new CameraImagePicker(this);

        recyclerViewQuestionnaire.setNestedScrollingEnabled(false);
        recyclerViewQuestionnaire.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestionnaire.addItemDecoration(new SpaceItemDecoration(OtherUtils.dpToPx(8)));
        questionnaireAdapter.setAnswerQuestionListener(this);
        recyclerViewQuestionnaire.setAdapter(questionnaireAdapter);

        questionnairePresenter.getQuestionsByIds(getIntent().getStringArrayListExtra(INTENT_KEY_QUESTION_IDS));
    }

    @Override
    protected void detachView() {
        questionnairePresenter.detachView();
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
    }

    @Override
    protected void emptyButtonClick() {
    }

    @Override
    protected void errorButtonClick() {
    }

    @Override
    public void setQuestions(List<Question> questions) {
        if (!questions.isEmpty()) {
            setTitle(questions.get(0).getGroupName().toString());
        }
        questionnaireAdapter.setData(questions);
        setStateData();
    }

    @Override
    public void answerQuestion(Question question) {
    }

    @Override
    public void pickDate(Calendar calendar, QuestionnaireAdapter.AddNewDateListener addNewDateListener) {
        DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth) -> {
                    TimePickerDialog.newInstance(
                            (view1, hourOfDay, minute, second) -> {
                                Calendar newCalendar = Calendar.getInstance();
                                newCalendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute, second);
                                addNewDateListener.addNewDate(newCalendar.getTime());
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                    ).show(getFragmentManager(), "TimePickerDialog");
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show(getFragmentManager(), "DatePickerDialog");
    }

    @SuppressLint("CheckResult")
    @Override
    public void pickImage(final QuestionnaireAdapter.AddNewPhotoListener addNewPhotoListener) {
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        cameraImagePicker.setImagePickerCallback(
                                new ImagePickerCallback() {
                                    @Override
                                    public void onImagesChosen(List<ChosenImage> images) {
                                        addNewPhotoListener.addNewPhoto(images.get(0).getOriginalPath());
                                    }

                                    @Override
                                    public void onError(String message) {
                                    }
                                }
                        );
                        outputPath = cameraImagePicker.pickImage();
                    } else {
                        DialogUtils.createPhotoPermissionDialog(this, (dialog, which) -> {
                            this.addNewPhotoListener = addNewPhotoListener;
                            startActivityForResult(
                                    new Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getPackageName(), null)
                                    ),
                                    REQ_SETTINGS
                            );
                        }).show();
                    }
                });
    }

}