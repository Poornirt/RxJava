import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

public class ObservableExample1 {

    public static void main(String[] args) {

        String[] alphabetList=new String[]{"q","r","s","u","v","w","x","y","z","p"};

        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (String s:alphabetList) {
                    emitter.onNext(s);
                }

                emitter.onComplete();
            }

        });

        Observer observer = new Observer() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("on Subscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("On Complete");
            }
        };

        observable.subscribe(observer);

    }
}
