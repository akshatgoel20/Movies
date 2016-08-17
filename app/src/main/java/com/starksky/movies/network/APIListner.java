package com.starksky.movies.network;


public final class APIListner {

    public interface Success<T> {
        public abstract void onResponse(T response);
    }

    public interface Error {
        public abstract void onErrorResponse(APIErrorException error);
    }
}
