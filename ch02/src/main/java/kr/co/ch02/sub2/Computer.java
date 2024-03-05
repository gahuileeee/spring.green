package kr.co.ch02.sub2;

import io.micrometer.observation.transport.Propagator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("com")
public class Computer {
    /*
    private Cpu cpu;
    private  Ram ram;
    private  Hdd hdd;
    */

    //필드 주입
    @Autowired
    private  Cpu cpu;//z

    @Autowired
    private  Ram ram;

    @Autowired
    private  Hdd hdd;

    //생성자 주입
    @Autowired
    public  Computer(Cpu cpu, Ram ram){
        this.cpu= cpu;
        this.ram=ram;
    }

    //세터 주입
    private Hdd hd;

    @Autowired
    public  void  setHd(Hdd hd){
        this.hdd=hd;
    }


    public   void  show(){
        cpu.show();
        ram.show();
        hdd.show();
    }
}
