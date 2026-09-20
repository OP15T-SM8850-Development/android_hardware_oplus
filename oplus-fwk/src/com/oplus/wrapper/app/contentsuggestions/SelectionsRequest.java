package com.oplus.wrapper.app.contentsuggestions;

public final class SelectionsRequest {
    SelectionsRequest() {
    }

    public static final class Builder {
        public Builder(int taskId) {
        }

        public Builder setExtras(android.os.Bundle extras) {
            return this;
        }

        public SelectionsRequest build() {
            return new SelectionsRequest();
        }
    }
}
