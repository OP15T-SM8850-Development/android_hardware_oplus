package com.oplus.wrapper.app.contentsuggestions;

import java.util.concurrent.Executor;

public class ContentSuggestionsManager {
    public static final String EXTRA_BITMAP = "android.appprediction.extra.BITMAP";

    public ContentSuggestionsManager(android.content.Context context) {
    }

    public void classifyContentSelections(ClassificationsRequest request, Executor callbackExecutor, ClassificationsCallback callback) {
    }

    public void suggestContentSelections(SelectionsRequest request, Executor callbackExecutor, SelectionsCallback callback) {
    }

    public void notifyInteraction(String requestId, android.os.Bundle interaction) {
    }

    public void provideContextImage(int taskId, android.os.Bundle imageContextRequestExtras) {
    }

    public interface ClassificationsCallback {
        void onContentClassificationsAvailable(int statusCode, java.util.List classifications);
    }

    public interface SelectionsCallback {
        void onContentSelectionsAvailable(int statusCode, java.util.List selections);
    }
}
