package Exercicio4;

class Conta {

    private int nemero; 
    private String tipo;
    private double saldo;
    private Cliente cliente;

    Conta(int nemero, String tipo, double saldo, Cliente cliente){
        this.nemero = nemero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.cliente = cliente;
    } 

     

    public double consultarSaldo(){
        return saldo;
    }

    public void depositar(double valor ){
        if(valor > 0){
            saldo += saldo;
            System.out.println("Saque de R$ " + valor + " relizado com sucesso.");
            } else {
                System.out.println("Valor de depòsito inválido");
            }
        }
    public boolean sacar(double valor){
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " relizado com sucesso." );
            return true;
        } else {
            System.out.println("Saldo insugiciente ou valor inválido.");
            return false;
        }
    }
    
}
