package info.novatec.testit.webtester.api.callbacks;

/**
 * A functional callback interface with one parameter of type A and a return
 * value of type B.
 *
 * @param <A> the input type of the callback method
 * @param <B> the return type of the callback method
 * @since 0.9.0
 */
public interface CallbackWithReturnValue<A, B> {

    /**
     * This method is called by an action template and contains the logic of the
     * action to execute.
     *
     * @param arg the input parameter
     * @return the return value
     * @since 0.9.0
     */
    B execute(A arg);

}
