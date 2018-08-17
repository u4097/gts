package ru.panmin.gtspro.ui.questionnaire;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Answer;
import ru.panmin.gtspro.data.models.Option;
import ru.panmin.gtspro.data.models.Photo;
import ru.panmin.gtspro.data.models.Question;
import ru.panmin.gtspro.ui.fullscreenimage.FullscreenImageActivity;
import ru.panmin.gtspro.utils.MultiChoiceAdapter;
import ru.panmin.gtspro.utils.SingleChoiceAdapter;

public class QuestionnaireAdapter extends RecyclerView.Adapter<QuestionnaireAdapter.BaseQuestionViewHolder> {

    private List<Question> questions = new ArrayList<>();
    private AnswerQuestionListener answerQuestionListener;

    @Inject
    QuestionnaireAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @NonNull
    @Override
    public BaseQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case 1:
                view = layoutInflater.inflate(R.layout.item_question_type_1, parent, false);
                return new QuestionType1ViewHolder(view);
            case 2:
                view = layoutInflater.inflate(R.layout.item_question_type_2, parent, false);
                return new QuestionType2ViewHolder(view);
            case 3:
                view = layoutInflater.inflate(R.layout.item_question_type_3, parent, false);
                return new QuestionType3ViewHolder(view);
            case 4:
                view = layoutInflater.inflate(R.layout.item_question_type_4, parent, false);
                return new QuestionType4ViewHolder(view);
            case 5:
                view = layoutInflater.inflate(R.layout.item_question_type_5, parent, false);
                return new QuestionType5ViewHolder(view);
            case 6:
                view = layoutInflater.inflate(R.layout.item_question_type_6, parent, false);
                return new QuestionType6ViewHolder(view);
            case 7:
                view = layoutInflater.inflate(R.layout.item_question_type_7, parent, false);
                return new QuestionType7ViewHolder(view);
            case 8:
                view = layoutInflater.inflate(R.layout.item_question_type_8, parent, false);
                return new QuestionType8ViewHolder(view);
            default:
                view = layoutInflater.inflate(R.layout.item_question_type_1, parent, false);
                return new QuestionType1ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseQuestionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return (questions == null || questions.isEmpty()) ? 0 : questions.size();
    }

    public void setData(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    private Question getItem(int position) {
        return questions.get(position);
    }

    public void setAnswerQuestionListener(AnswerQuestionListener answerQuestionListener) {
        this.answerQuestionListener = answerQuestionListener;
    }

    interface AnswerQuestionListener {

        void pickImage(AddNewPhotoListener addNewPhotoListener);

        void answerQuestion(Question question);

    }

    interface AddNewPhotoListener {

        void addNewPhoto(String path);

    }

    interface PhotoClickListener {

        void removePhoto(Photo photo);

    }

    abstract class BaseQuestionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutQuestionnaireQuestion) RelativeLayout layoutQuestionnaireQuestion;
        @BindView(R.id.textQuestionnaireQuestionRequired) AppCompatTextView textQuestionnaireQuestionRequired;
        @BindView(R.id.textQuestionnaireQuestion) AppCompatTextView textQuestionnaireQuestion;

        BaseQuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Question question) {
            textQuestionnaireQuestion.setText(question.getName().toString());
            afterBaseBind(question);
        }

        abstract void afterBaseBind(Question question);

    }

    class QuestionType1ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.buttonQuestionnaireNo) AppCompatButton buttonQuestionnaireNo;
        @BindView(R.id.buttonQuestionnaireYes) AppCompatButton buttonQuestionnaireYes;

        QuestionType1ViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        void afterBaseBind(Question question) {
            buttonQuestionnaireNo.setOnClickListener(view -> {
                buttonQuestionnaireYes.setBackgroundResource(R.drawable.azure_button_background);
                buttonQuestionnaireNo.setBackgroundResource(R.drawable.strawberry_button_background);

                question.getRealm().beginTransaction();
                Answer answer = question.getAnswer();
                if (answer == null) {
                    answer = new Answer(false);
                    answer = question.getRealm().copyToRealmOrUpdate(answer);
                }
                question.setAnswer(answer);
                question.getRealm().commitTransaction();

                answerQuestionListener.answerQuestion(question);
            });

            buttonQuestionnaireNo.setOnClickListener(view -> {
                buttonQuestionnaireNo.setBackgroundResource(R.drawable.azure_button_background);
                buttonQuestionnaireYes.setBackgroundResource(R.drawable.strawberry_button_background);

                question.getRealm().beginTransaction();
                Answer answer = question.getAnswer();
                if (answer == null) {
                    answer = new Answer(true);
                    answer = question.getRealm().copyToRealmOrUpdate(answer);
                }
                question.setAnswer(answer);
                question.getRealm().commitTransaction();

                answerQuestionListener.answerQuestion(question);
            });
        }

    }

    class QuestionType2ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.editTextQuestionnaireAnswer) AppCompatEditText editTextQuestionnaireAnswer;

        QuestionType2ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void afterBaseBind(Question question) {
            editTextQuestionnaireAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String stringAnswer = editable.toString().trim();

                    question.getRealm().beginTransaction();
                    Answer answer = question.getAnswer();
                    if (TextUtils.isEmpty(stringAnswer)) {
                        if (answer != null) {
                            answer.deleteFromRealm();
                            answer = null;
                        }
                    } else {
                        if (answer == null) {
                            answer = new Answer(Double.parseDouble(stringAnswer));
                        } else {
                            answer.setDoubleValue(Double.parseDouble(stringAnswer));
                        }
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                    }
                    question.setAnswer(answer);
                    question.getRealm().commitTransaction();

                    answerQuestionListener.answerQuestion(question);
                }
            });
        }

    }

    class QuestionType3ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.recyclerViewQuestionnaireAnswer) RecyclerView recyclerViewQuestionnaireAnswer;

        private OptionSingleChoiceAdapter optionSingleChoiceAdapter;

        QuestionType3ViewHolder(View itemView) {
            super(itemView);
            optionSingleChoiceAdapter = new OptionSingleChoiceAdapter();
        }

        @Override
        void afterBaseBind(Question question) {
            if (optionSingleChoiceAdapter.getItemCount() == 0) {
                optionSingleChoiceAdapter.setItems(question.getOptions());
            }
            recyclerViewQuestionnaireAnswer.setNestedScrollingEnabled(false);
            recyclerViewQuestionnaireAnswer.setLayoutManager(new LinearLayoutManager(recyclerViewQuestionnaireAnswer.getContext()));
            recyclerViewQuestionnaireAnswer.setAdapter(optionSingleChoiceAdapter);
        }

        public class OptionSingleChoiceAdapter extends SingleChoiceAdapter<Option> {

            OptionSingleChoiceAdapter() {
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.item_option, parent, false);
                return new OptionViewHolder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((OptionViewHolder) holder).bind(getItem(position), position);
            }

            class OptionViewHolder extends RecyclerView.ViewHolder {

                @BindView(R.id.imageOptionChecked) AppCompatImageView imageOptionChecked;
                @BindView(R.id.textOptionName) AppCompatTextView textOptionName;

                OptionViewHolder(View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }

                void bind(Option option, int position) {
                    imageOptionChecked.setImageResource(
                            isChoiceItem(option, position)
                                    ?
                                    R.drawable.ic_baseline_check_circle_24px
                                    :
                                    R.drawable.ic_baseline_radio_button_unchecked_24px
                    );

                    textOptionName.setText(option.getId());

                    setChoiceClickListener(
                            itemView,
                            () -> {

                            },
                            option,
                            position
                    );
                }

            }

        }

    }

    class QuestionType4ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.recyclerViewQuestionnaireAnswers) RecyclerView recyclerViewQuestionnaireAnswers;

        private OptionMultiChoiceAdapter optionMultiChoiceAdapter;

        QuestionType4ViewHolder(View itemView) {
            super(itemView);
            optionMultiChoiceAdapter = new OptionMultiChoiceAdapter();
        }

        @Override
        void afterBaseBind(Question question) {
            if (optionMultiChoiceAdapter.getItemCount() == 0) {
                optionMultiChoiceAdapter.setItems(question.getOptions());
            }
            recyclerViewQuestionnaireAnswers.setNestedScrollingEnabled(false);
            recyclerViewQuestionnaireAnswers.setLayoutManager(new LinearLayoutManager(recyclerViewQuestionnaireAnswers.getContext()));
            recyclerViewQuestionnaireAnswers.setAdapter(optionMultiChoiceAdapter);
        }

        public class OptionMultiChoiceAdapter extends MultiChoiceAdapter<Option> {

            @BindView(R.id.imageOptionChecked) AppCompatImageView imageOptionChecked;
            @BindView(R.id.textOptionName) AppCompatTextView textOptionName;

            OptionMultiChoiceAdapter() {
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.item_option, parent, false);
                return new OptionViewHolder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((OptionViewHolder) holder).bind(getItem(position), position);
            }

            class OptionViewHolder extends RecyclerView.ViewHolder {

                OptionViewHolder(View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }

                void bind(Option option, int position) {
                    imageOptionChecked.setImageResource(
                            isChoiceItem(position)
                                    ?
                                    R.drawable.ic_baseline_check_circle_24px
                                    :
                                    R.drawable.ic_baseline_radio_button_unchecked_24px
                    );

                    textOptionName.setText(option.getId());

                    setChoiceClickListener(
                            itemView,
                            () -> {

                            },
                            position
                    );
                }

            }

        }

    }

    class QuestionType5ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.editTextQuestionnaireAnswer) AppCompatEditText editTextQuestionnaireAnswer;

        QuestionType5ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void afterBaseBind(Question question) {
            editTextQuestionnaireAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String stringAnswer = editable.toString().trim();

                    question.getRealm().beginTransaction();
                    Answer answer = question.getAnswer();
                    if (TextUtils.isEmpty(stringAnswer)) {
                        if (answer != null) {
                            answer.deleteFromRealm();
                            answer = null;
                        }
                    } else {
                        if (answer == null) {
                            answer = new Answer(stringAnswer);
                        } else {
                            answer.setStringValue(stringAnswer);
                        }
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                    }
                    question.setAnswer(answer);
                    question.getRealm().commitTransaction();

                    answerQuestionListener.answerQuestion(question);
                }
            });
        }

    }

    class QuestionType6ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.textQuestionnaireDate) AppCompatTextView textQuestionnaireDate;

        QuestionType6ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void afterBaseBind(Question question) {
            textQuestionnaireDate.setText(R.string.select_date);
        }

    }

    class QuestionType7ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.editTextQuestionnaireAnswer) AppCompatEditText editTextQuestionnaireAnswer;

        QuestionType7ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void afterBaseBind(Question question) {
            editTextQuestionnaireAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String stringAnswer = editable.toString().trim();

                    question.getRealm().beginTransaction();
                    Answer answer = question.getAnswer();
                    if (TextUtils.isEmpty(stringAnswer)) {
                        if (answer != null) {
                            answer.deleteFromRealm();
                            answer = null;
                        }
                    } else {
                        if (answer == null) {
                            answer = new Answer(Integer.parseInt(stringAnswer));
                        } else {
                            answer.setIntegerValue(Integer.parseInt(stringAnswer));
                        }
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                    }
                    question.setAnswer(answer);
                    question.getRealm().commitTransaction();

                    answerQuestionListener.answerQuestion(question);
                }
            });
        }

    }

    class QuestionType8ViewHolder extends BaseQuestionViewHolder {

        @BindView(R.id.recyclerViewQuestionnairePhotos) RecyclerView recyclerViewQuestionnairePhotos;

        private PhotoAdapter photoAdapter;

        QuestionType8ViewHolder(View itemView) {
            super(itemView);
            photoAdapter = new PhotoAdapter();
        }

        @Override
        void afterBaseBind(Question question) {
            if (!photoAdapter.isHaveListener()) {
                photoAdapter.setAddNewPhotoListener(path -> {
                    question.getRealm().beginTransaction();
                    Answer answer = question.getAnswer();
                    if (answer == null) {
                        RealmList<Photo> photos = new RealmList<>();
                        Photo photo = new Photo(path);
                        photo = question.getRealm().copyToRealmOrUpdate(photo);
                        photos.add(photo);
                        answer = new Answer(photos);
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                    } else {
                        Photo photo = new Photo(path);
                        photo = question.getRealm().copyToRealmOrUpdate(photo);
                        answer.getPhotoList().add(photo);
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                    }
                    question.setAnswer(answer);
                    question.getRealm().commitTransaction();
                    notifyDataSetChanged();
                });
                photoAdapter.setPhotoClickListener(photo -> {
                    question.getRealm().beginTransaction();
                    Answer answer = question.getAnswer();

                    answer.getPhotoList().remove(photo);
                    if (answer.getPhotoList().isEmpty()){
                        answer.deleteFromRealm();
                        question.setAnswer(null);
                    } else {
                        answer = question.getRealm().copyToRealmOrUpdate(answer);
                        question.setAnswer(answer);
                    }

                    question.getRealm().commitTransaction();
                    notifyDataSetChanged();
                });
            }
            if (question.getAnswer() != null
                    && question.getAnswer().getPhotoList() != null
                    && photoAdapter.getItemCount() != question.getAnswer().getPhotoList().size() + 1) {
                photoAdapter.setItems(question.getAnswer().getId(), question.getAnswer().getPhotoList());

            }
            recyclerViewQuestionnairePhotos.setLayoutManager(
                    new LinearLayoutManager(recyclerViewQuestionnairePhotos.getContext(), LinearLayoutManager.HORIZONTAL, false)
            );
            recyclerViewQuestionnairePhotos.setAdapter(photoAdapter);
        }

        public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

            private static final int VIEW_TYPE_HEADER = 0;
            private static final int VIEW_TYPE_PHOTO = 1;

            private static final int HEADER_POSITION = 0;
            private static final int HEADER_WEIGHT = 1;

            private String answerId = "";
            private List<Photo> photos = new ArrayList<>();

            private AddNewPhotoListener addNewPhotoListener;
            private PhotoClickListener photoClickListener;

            PhotoAdapter() {
            }

            @Override
            public int getItemViewType(int position) {
                return position == HEADER_POSITION ? VIEW_TYPE_HEADER : VIEW_TYPE_PHOTO;
            }

            @Override
            public int getItemCount() {
                return ((photos == null || photos.isEmpty()) ? 0 : photos.size()) + HEADER_WEIGHT;
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view;
                switch (viewType) {
                    case VIEW_TYPE_HEADER:
                        view = layoutInflater.inflate(R.layout.item_photo_header_add, parent, false);
                        return new HeaderAddPhotoViewHolder(view);
                    case VIEW_TYPE_PHOTO:
                        view = layoutInflater.inflate(R.layout.item_photo, parent, false);
                        return new PhotoViewHolder(view);
                    default:
                        view = layoutInflater.inflate(R.layout.item_photo_header_add, parent, false);
                        return new HeaderAddPhotoViewHolder(view);
                }
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                switch (getItemViewType(position)) {
                    case VIEW_TYPE_HEADER:
                        ((HeaderAddPhotoViewHolder) holder).bind();
                        break;
                    case VIEW_TYPE_PHOTO:
                        ((PhotoViewHolder) holder).bind(getItem(position), position - HEADER_WEIGHT);
                        break;
                }
            }

            private Photo getItem(int position) {
                return photos.get(position - HEADER_WEIGHT);
            }

            void setItems(String answerId, List<Photo> photos) {
                this.answerId = answerId;
                this.photos.clear();
                this.photos.addAll(photos);
                notifyDataSetChanged();
            }

            void setAddNewPhotoListener(AddNewPhotoListener addNewPhotoListener) {
                this.addNewPhotoListener = addNewPhotoListener;
            }

            void setPhotoClickListener(PhotoClickListener photoClickListener) {
                this.photoClickListener = photoClickListener;
            }

            boolean isHaveListener() {
                return addNewPhotoListener != null && photoClickListener != null;
            }

            class HeaderAddPhotoViewHolder extends RecyclerView.ViewHolder {

                HeaderAddPhotoViewHolder(View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }

                void bind() {
                    itemView.setOnClickListener(view -> answerQuestionListener.pickImage(addNewPhotoListener));
                }

            }

            class PhotoViewHolder extends RecyclerView.ViewHolder {

                @BindView(R.id.imagePhoto) AppCompatImageView imagePhoto;
                @BindView(R.id.imagePhotoComment) AppCompatImageView imagePhotoComment;
                @BindView(R.id.imagePhotoRemove) AppCompatImageView imagePhotoRemove;

                PhotoViewHolder(View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }

                void bind(Photo photo, int position) {
                    Glide.with(imagePhoto)
                            .load(photo.getUrl())
                            .into(imagePhoto);

                    imagePhotoComment.setOnClickListener(view -> {
                    });
                    imagePhotoRemove.setOnClickListener(view -> photoClickListener.removePhoto(photo));
                    itemView.setOnClickListener(view -> itemView.getContext().startActivity(
                            FullscreenImageActivity.getStartIntent(itemView.getContext(), answerId, position)
                    ));
                }

            }

        }

    }

}