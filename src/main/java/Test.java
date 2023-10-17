import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;

/**
 * @author waani
 * @date 2023/10/13 16:57
 * @description TODO
 */
public class Test {


    public static void main(String[] args) {
        // 球选择
        AbstractSelect select = new BallSelect();
        System.out.println(select.selectTag());
        System.out.println(select.selectTag());
        System.out.println(select.selectTag());
    }

}
