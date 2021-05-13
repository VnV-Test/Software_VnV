class ImListening extends Thread {
    public VM vm;

    public ImListening(VM v){
        super("Im Listening");
        this.vm = v;
    }
    @Override
    public void run(){
        while(true){
            if(vm.getMailBoxSize() > 0) {

                vm.receiveRequest();
            }
        }
    }
}