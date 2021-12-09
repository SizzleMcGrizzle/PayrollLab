module me.p3074098.payrolllab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires org.yaml.snakeyaml;
    
    exports me.p3074098.payrolllab;
    opens me.p3074098.payrolllab to javafx.fxml;
    exports me.p3074098.payrolllab.controller;
    opens me.p3074098.payrolllab.controller to javafx.fxml;
    exports me.p3074098.payrolllab.workers to javafx.fxml;
    opens me.p3074098.payrolllab.workers;
}