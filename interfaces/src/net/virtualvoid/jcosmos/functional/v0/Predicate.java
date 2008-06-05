/**
 *
 */
package net.virtualvoid.jcosmos.functional.v0;


public interface Predicate<T> extends F1<T,Boolean>{
	Predicate<T> and(Predicate<T> t2);
	Predicate<T> inverse();
	boolean predicate(T v);
	<U> Predicate<U> ofChild(F1<U,T> func);
}