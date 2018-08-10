package ru.panmin.gtspro.ui.blocks.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import ru.panmin.gtspro.R;

public class BlockType {

    public Type blockType;
    @StringRes
    Integer titleRes;
    @DrawableRes
    Integer svIconRes;
    @DrawableRes
    Integer meIconRes;


    public BlockType(Type blockType) {
        this.blockType = blockType;

        this.svIconRes = R.drawable.btn_claims_sv;
        this.meIconRes = R.drawable.btn_claims_me;

        switch (blockType) {
            case CLAIMS: {
                this.titleRes = R.string.label_claims;
                break;
            }
            case PROMO: {
                this.titleRes = R.string.label_promo;
                break;
            }
            case PHOTO_REPORT: {
                this.titleRes = R.string.label_photo_report;
                break;
            }
            case REPORT: {
                this.titleRes = R.string.label_report;
                break;
            }
            case SKU: {
                this.titleRes = R.string.label_sku;
                break;
            }
            case PLANOGRAM: {
                this.titleRes = R.string.label_planogram;
                break;
            }
            case HOT_LINE: {
                this.titleRes = R.string.label_hot_line;
                break;
            }
            case STATISTICS: {
                this.titleRes = R.string.label_statistics;
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid trade point block type.");
        }

    }

    public Type getBlockType() {
        return blockType;
    }

    public void setBlockType(Type blockType) {
        this.blockType = blockType;
    }

    public Integer getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(Integer titleRes) {
        this.titleRes = titleRes;
    }

    public Integer getSvIconRes() {
        return svIconRes;
    }

    public void setSvIconRes(Integer svIconRes) {
        this.svIconRes = svIconRes;
    }

    public Integer getMeIconRes() {
        return meIconRes;
    }

    public void setMeIconRes(Integer meIconRes) {
        this.meIconRes = meIconRes;
    }

    public enum Type {
        CLAIMS,
        PROMO,
        PHOTO_REPORT,
        REPORT,
        SKU,
        PLANOGRAM,
        HOT_LINE,
        STATISTICS
    }
}
