class ImListening extends Thread {
    public VM vm;

    public ImListening(VM v){
        super("Im Listening");
        this.vm = v;
    }
    @Override
    public void run(){
        while(true){
            if(vm.getMailBoxSize(1) > 0 || vm.mailBox[4].size() > 0){
                vm.NotifyVMsInfo();
            }
            if(vm.getMailBoxSize(2) > 0){
                vm.getOtherVM_2();
            }
            if(vm.getMailBoxSize(3) > 0){
                vm.confirmPrepay();
            }
            if(vm.getMailBoxSize(5) > 0){
                vm.getOtherVM_3();
            }
            if(vm.getMailBoxSize(6) > 0){
                vm.RespondSell();
            }
            if(vm.getMailBoxSize(7) > 0){
                vm.ConfirmSell_2();
            }
            if(vm.getMailBoxSize(8) > 0){
                vm.requestPrepay_2();
            }
        }
    }
}