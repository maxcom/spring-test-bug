import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/")
public class SimpleController {
  final ExecutorService executor = Executors.newCachedThreadPool();

  @RequestMapping
  @ResponseBody
  public DeferredResult<String> handle() {
    final DeferredResult<String> result = new DeferredResult<>();

    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(ThreadLocalRandom.current().nextInt(100));
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        result.setResult("result");
      }
    });

    return result;
  }
}
