class Conta {
    String tipo;
    double saldo;
    int numeroConta;
    int idCliente;

    // Construtor da classe Conta
    public Conta(String tipo,  int numeroConta, int idCliente) {
        this.tipo = tipo;
        this.saldo = 0;
        this.numeroConta = numeroConta;
        this.idCliente = idCliente;
    }

    

    public String getTipo() {
        return tipo;
    }



    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public double getSaldo() {
        return saldo;
    }



    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    public int getNumeroConta() {
        return numeroConta;
    }



    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }



    public int getIdCliente() {
        return idCliente;
    }



    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }



    double consultarSaldo() {
        return this.saldo;
    }

    void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Valor informado não é válido!");
        }
    }

    void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            System.out.println("Valor informado não é válido para o saque!");
        }
    }

    void transferir(double valor, Conta contaTransfere) {
        if (valor > 0 && valor <= this.saldo) {
            this.sacar(valor);
            contaTransfere.depositar(valor);
        } else {
            System.out.println("Transferência não realizada. Verifique o saldo disponível.");
        }
    }
}