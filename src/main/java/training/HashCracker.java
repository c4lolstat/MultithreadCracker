package training;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HashCracker {

    public static volatile boolean done;

    private final int NUMBER_OF_CORES = 2;// Runtime.getRuntime().availableProcessors();
    private String searchHashCode;
    private ExecutorService executor;
    private CompletionService<String> completionService;
    private int wordLength;
    private List<Future<String>> threadList = new ArrayList<>();

    public HashCracker(String searchHashCode) {
        this.searchHashCode = searchHashCode;
        this.executor = Executors.newFixedThreadPool(NUMBER_OF_CORES);
        this.completionService = new ExecutorCompletionService<>(executor);
        this.wordLength = 0;
    }

    public String crack() {
        startSomeNewExecutor();
        
        String pass = "";
        while (true) {
            try {
                pass = checkExecutorAnswear();
                if (pass.isEmpty()) {
                    submitNewHashCracker();
                }else {
                    break;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        return pass;

    }

    private void startSomeNewExecutor() {
        for (int i = 0; i < NUMBER_OF_CORES; i++) {
            submitNewHashCracker();
        }
    }

    private void submitNewHashCracker() {
        Future<String> thread = completionService.submit(new HashCrackerCallable(++wordLength, searchHashCode));
        threadList.add(thread);
    }

    private String checkExecutorAnswear() throws InterruptedException, ExecutionException {
        String pass = completionService.take().get();
        if (!pass.isEmpty()) {
            HashCracker.done = true;
            return pass;
        }
        return "";
    }

}
