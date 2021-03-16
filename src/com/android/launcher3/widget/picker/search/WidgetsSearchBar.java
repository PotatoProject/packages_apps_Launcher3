/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3.widget.picker.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.launcher3.R;
import com.android.launcher3.search.SearchAlgorithm;
import com.android.launcher3.widget.model.WidgetsListBaseEntry;

import java.util.List;

/**
 * View for a search bar with an edit text with a cancel button.
 */
public class WidgetsSearchBar extends LinearLayout {
    private WidgetsSearchBarController mController;
    private EditText mEditText;
    private ImageButton mCancelButton;

    public WidgetsSearchBar(Context context) {
        this(context, null, 0);
    }

    public WidgetsSearchBar(@NonNull Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetsSearchBar(@NonNull Context context, @Nullable AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Attaches a controller to the search bar which interacts with {@code searchModeListener}.
     */
    public void initialize(List<WidgetsListBaseEntry> allWidgets,
            SearchModeListener searchModeListener) {
        SearchAlgorithm<WidgetsListBaseEntry> algo =
                new SimpleWidgetsSearchAlgorithm(new SimpleWidgetsSearchPipeline(allWidgets));
        mController = new WidgetsSearchBarController(
                algo, mEditText, mCancelButton, searchModeListener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mEditText = findViewById(R.id.widgets_search_bar_edit_text);
        mCancelButton = findViewById(R.id.widgets_search_cancel_button);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mController.onDestroy();
    }
}
