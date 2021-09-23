import com.example.demo.dao.TankDaoImpl;
import com.example.demo.exception.MyException;
import com.example.demo.service.TankService;
import com.example.demo.service.TankServiceImpl;

public class Work {
    public static void main(String[] args) throws MyException {

         final TankService service = new TankServiceImpl(new TankDaoImpl());

         service.createTanks(4);
         service.connectTo(1,2);


    }
}
