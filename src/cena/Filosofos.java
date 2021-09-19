/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cena;

/**
 *
 * @author caballo
 */
import java.util.Random;
import java.util.concurrent.Semaphore;
public class Filosofos extends Thread {
    private final int idFilosofo;
    private final Semaphore [] idpalillos;
    private final int [][] palillosParaComer;
    private final int palilloIzquierdo;
    private final int palilloDerecho;
    private final Random tiempoAleatorio= new Random();
    public Filosofos(int idFilosofo, Semaphore [] idpalillos, int [][] palillosParaComer){
        this.idFilosofo=idFilosofo;
        this.idpalillos=idpalillos;
        this.palillosParaComer=palillosParaComer;
        this.palilloIzquierdo=palillosParaComer[idFilosofo][0];
        this.palilloDerecho=palillosParaComer[idFilosofo][1];
    }
    protected void comer(){
        if (idpalillos[palilloIzquierdo].tryAcquire()){
            if(idpalillos[palilloDerecho].tryAcquire()){
                System.out.println("EL FILOSOFO "+idFilosofo+" ESTA COMIENDO");
                try{
                    sleep(tiempoAleatorio.nextInt(1000)+500);
                }catch (InterruptedException ex){
                }
                System.out.println("El filosofo "+idFilosofo+" ha terminado de comer. Suelta el palillo izquierdo ("+palilloIzquierdo+") y el palillo derecho ("
                +palilloDerecho+").");
                idpalillos[palilloDerecho].release();
            }
                idpalillos[palilloIzquierdo].release();
        }else {
            System.out.println("el filosofo "+idFilosofo+" esta hambriento.");
        }
    }
    
}
