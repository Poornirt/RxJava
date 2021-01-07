import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

public class ObservableExample6 {

    Observable getObservable(){
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(Integer integer:integers) {
                    if(!emitter.isDisposed())
                    emitter.onNext(integer);
                }
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    public static void main(String[] args) {

        ObservableExample6 observableExample6 = new ObservableExample6();

        observableExample6.getObservable().map(new Function<Integer,Integer>() {
            @Override
            public Integer apply(Integer o) throws Throwable {
                return o*10;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("on Next"+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
