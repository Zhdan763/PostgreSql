package util;

public interface Converter<T,S> {
    T convertDoMainToEntity(S s);

    S convertEntityToDoMain(T t);
}
