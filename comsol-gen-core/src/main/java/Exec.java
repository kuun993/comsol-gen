import com.comsol.gen.Run;
import com.comsol.model.Model;

public class Exec {

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException();
        }
        String taskId = args[0];
        String configPath = args[1];
        try {
            run(taskId, configPath);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e.getMessage());
        }
    }


    private static Model run(String taskId, String configPath) {
        return Run.start(taskId, configPath);
    }


}
