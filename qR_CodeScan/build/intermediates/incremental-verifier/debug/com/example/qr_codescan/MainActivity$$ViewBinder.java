// Generated code from Butter Knife. Do not modify!
package com.example.qr_codescan;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.qr_codescan.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165210, "field '_mDizhi'");
    target._mDizhi = finder.castView(view, 2131165210, "field '_mDizhi'");
    view = finder.findRequiredView(source, 2131165211, "field '_mSuiji' and method 'onClick'");
    target._mSuiji = finder.castView(view, 2131165211, "field '_mSuiji'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165216, "field '_mQingkong' and method 'onClick'");
    target._mQingkong = finder.castView(view, 2131165216, "field '_mQingkong'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target._mDizhi = null;
    target._mSuiji = null;
    target._mQingkong = null;
  }
}
