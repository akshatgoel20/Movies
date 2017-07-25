package com.starksky.movies.network;


public final class APIListner {

    public interface Success<T> {
        void onResponse(T response);
    }

    public interface Error {
        void onErrorResponse(APIErrorException error);
    }
}
