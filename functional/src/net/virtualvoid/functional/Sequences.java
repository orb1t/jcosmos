package net.virtualvoid.functional;

import static java.text.MessageFormat.format;
import net.virtualvoid.functional.Functions.Function1;
import net.virtualvoid.functional.Functions.Function2;
import net.virtualvoid.functional.Predicates.Predicate;

public class Sequences {
	public static <T> Seq<T> unfold(final T outerStart,final Function1<? super T,T> succ,final Predicate<? super T> stopCondition){
		return new AbstractRichSequence<T>(){
			public <U> U fold(Function2<? super U, ? super T, U> func, U start) {
				T value = outerStart;
				while(!stopCondition.predicate(value)){
					start = func.apply(start, value);
					value = succ.apply(value);
				}
				return start;
			}
		};
	}
	public static RASeq<Integer> range(final Integer from, final int toExclusive){
		return new AbstractRandomAccessSequence<Integer>(){
			public Integer get(int index) {
				return from + index;
			}
			@Override
			public int length() {
				return toExclusive - from;
			}
			@Override
			public String toString() {
				return format("[{0,number}..{1,number})",from,toExclusive);
			}
		};
	}
	public final static <T>Seq<T> emptySequence(){
		return new AbstractRichSequence<T>(){
			public <U> U fold(Function2<? super U, ? super T, U> func, U start) {
				return start;
			}
			@Override
			public <U> Seq<U> map(Function1<? super T, U> func) {
				return emptySequence();
			}
			@Override
			public Seq<T> select(Predicate<? super T> predicate) {
				return this;
			}
			@Override
			public String toString() {
				return "[]";
			}
		};
	}
	public static <T> Seq<T> singleton(final T element){
		return new AbstractRichSequence<T>(){
			public <U> U fold(Function2<? super U, ? super T, U> func, U start) {
				return func.apply(start, element);
			}
			@Override
			public int length() {
				return 1;
			}
			@Override
			public String toString() {
				return format("[{0}]",element.toString());
			}
		};
	}
	public static <T> Seq<T> fromIterable(final Iterable<T> it){
		return new AbstractRichSequence<T>(){
			public <U> U fold(Function2<? super U, ? super T, U> func, U start) {
				for(T ele:it)
					start = func.apply(start, ele);
				return start;
			}
		};
	}
	public static <T> Seq<T> fromFoldable(final IFoldable<T> foldable){
		return new AbstractRichSequence<T>(){
			public <U> U fold(Function2<? super U, ? super T, U> func, U start) {
				return foldable.fold(func, start);
			}
		};
	}
	public static <T> RASeq<T> fromRandomAccessable(final IRandomAccessable<T> accessable){
		return new AbstractRandomAccessSequence<T>(){
			public T get(int index) {
				return accessable.get(index);
			}
			@Override
			public int length() {
				return accessable.length();
			}
		};
	}
	public static <T> Function2<Seq<T>,Seq<T>,Seq<T>> join(){
		return new Function2<Seq<T>,Seq<T>,Seq<T>>(){
			@Override
			public Seq<T> apply(Seq<T> arg1, Seq<T> arg2) {
				return arg1.join(arg2);
			}
		};
	}
}