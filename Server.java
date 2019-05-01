package pkg_safe;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;


    boolean converged = false;
    int iterations = 0;
    boolean isA = true;
    Model A;
    Model B;
    Model currentModel;
    public Server(Model A, Model B) {
        this.currentModel = A;
        this.A = A;
        this.B = B;
    }
        public void Run() {
            Controller c = new Controller(A,B,currentModel,0,0,A.myCells.length,A.myCells[0].length);
            ForkJoinPool pool = new ForkJoinPool();
            while (converged==false&& iterations <3456) {
                pool.invoke(c);
                System.out.println("Pool size "+pool.getPoolSize());
                if (isA==true){
                    A.updateView();
                    isA=false;
                    currentModel = B;
                } else if (isA==false){
                    B.updateView();
                    isA=true;
                    currentModel = A;
                }
                //c = new Controller(A,B,currentModel,0,0,A.myCells.length,A.myCells[0].length);
                //pool.invoke(c);

                //converged = checkConvergence(A,B);
            }
            if (isA==true){
                A.updateView();
            } else if (isA==false){
                B.updateView();
            }
        }

    static Boolean checkConvergence(Model modA, Model modB) {
        double diff = Math.abs(totalTemp(modB,modA));
        if (diff < 0.001) {
            return true;
        }
        return false;
    }
    static double totalTemp(Model mod, Model mod2){
        double total=0;
        ///for (int i = 0; i < mod.myCells.length; i++) {
            ///for (int j = 0; j < mod.myCells[0].length; j++) {
                ///total+= mod.myCells[i][j].temperature-mod2.myCells[i][j].temperature;
        ///    }
       /// }
        return total;
    }
    }

