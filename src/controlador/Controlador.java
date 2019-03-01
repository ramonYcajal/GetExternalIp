package controlador;

import controlador.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio Santiago Ramos
 * @date 10-may-2016
 * @time 18:10:07
 */
public class Controlador implements Runnable {

    private JTextArea texto;

    public Controlador() {

    }

    public Controlador(JTextArea texto) {
        this.texto = texto;
    }

    @Override
    public void run() {
        File f = new File("ip.txt");

        while (true) {
            try {
                URL miIp = new URL("http://checkip.amazonaws.com");
                // este método abre la página y devuelve un input stream para poder manejarlo
                InputStream is = miIp.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String str = br.readLine();
                if (f.exists()) {
                    // System.out.println("existe");
                    FileReader fr = new FileReader(f);
                    br = new BufferedReader(fr);
                    if (!str.equalsIgnoreCase(br.readLine())) {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(str);
                        br.close();
                        bw.close();
                        fw.close();
                        fr.close();
                    }

                } else {// o sea si no existe el fichero, me lo crea y lo guarda
                    FileWriter fw = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(str);
                    bw.close();
                    fw.close();
                }

                //System.out.println(c);
                texto.append(str + "\n");
                System.out.println(str);
                Thread.sleep(2000);

            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
