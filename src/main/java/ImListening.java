class ImListening extends Thread {
    public VM vm;

    public ImListening(VM v){
        super("Im Listening"+v.getIDtS());
        this.vm = v;
    }
    @Override
    public void run(){
        while(true){
            if(vm.getMailBoxSize() > 0) {
                System.out.println("메일박스 사이즈 늘어남.");
                vm.receiveRequest();
            }
        }
    }
}