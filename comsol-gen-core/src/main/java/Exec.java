import com.comsol.gen.Run;
import com.comsol.model.Model;

public class Exec {

    public static void main(String[] args) {
        try {
            if (args == null || args.length < 2) {
                throw new IllegalArgumentException("IllegalArgument");
            }
            String taskId = args[0];
            String configPath = args[1];
            System.out.println("*******************************************");
            System.out.println("submit args");
            System.out.println("taskId is " + taskId);
            System.out.println("config path is " + configPath);
            System.out.println("*******************************************");
            run(taskId, configPath);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println("Exec done.");
        }
    }


    private static Model run(String taskId, String configPath) {
        return Run.start(taskId, configPath);
    }


}
