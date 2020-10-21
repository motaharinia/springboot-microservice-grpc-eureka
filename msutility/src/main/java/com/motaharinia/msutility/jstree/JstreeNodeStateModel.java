package com.motaharinia.msutility.jstree;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *     این کلاس وضعیت هر گره درخت jstree میباشد
 */
public class JstreeNodeStateModel implements Serializable {

    /**
     * آیا گره درخت باز شده است؟
     */
    private boolean opened;

    /**
     * آیا گره درخت غیرفعال است؟
     */
    private boolean disabled;

    /**
     * آیا گره درخت انتخاب شده است؟
     */
    private boolean selected;

    /**
     * آیا گره درخت لود شده است؟
     */
    @JsonIgnore
    private boolean loaded;

    /**
     * متد سازنده پیش فرض
     */
    public JstreeNodeStateModel() {

    }

    /**
     * متد سازنده که با دریافت وضعیتها مدل را می سازد
     * @param opened  آیا گره درخت باز شده است؟
     * @param disabled  آیا گره درخت غیرفعال است؟
     * @param selected  آیا گره درخت انتخاب شده است؟
     */
    public JstreeNodeStateModel(boolean opened, boolean disabled, boolean selected) {
        this.opened = opened;
        this.disabled = disabled;
        this.selected = selected;
    }

    /**
     * متد سازنده که با دریافت وضعیتها مدل را می سازد
     * @param opened  آیا گره درخت باز شده است؟
     * @param disabled  آیا گره درخت غیرفعال است؟
     * @param selected  آیا گره درخت انتخاب شده است؟
     * @param loaded آیا گره درخت لود شده است؟
     */
    public JstreeNodeStateModel(boolean opened, boolean disabled, boolean selected, boolean loaded) {
        this.opened = opened;
        this.disabled = disabled;
        this.selected = selected;
        this.loaded = loaded;
    }


    //getter-setter:
    public boolean isOpened() {
        return opened;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isLoaded() {
        return loaded;
    }
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

}
