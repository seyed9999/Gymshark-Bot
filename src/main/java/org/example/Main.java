package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Notifier notifier = new Notifier();
        notifier.sendMsg("Hallo");

        InOrOut inOrOut = new InOrOut();
        System.out.println(inOrOut.checkClothes());
    }
}
