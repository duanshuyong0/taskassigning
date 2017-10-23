package taskassigning.rtp;

import java.util.ArrayList;
import java.util.List;

import org.kie.server.services.optaplanner.ContinuousPlanning;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.optaplanner.core.impl.solver.ProblemFactChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taskassigning.persistence.TaskAssigningGenerator;

public class TaskAssigningRealTimePlanning implements ContinuousPlanning {

    private static final Logger logger = LoggerFactory.getLogger(TaskAssigningRealTimePlanning.class);

    private static final String CONTAINER_ID = "taskassigning";
    private static final String SOLVER_ID = "taskassigning-solver";
    private static final String SOLVER_CONFIG_PATH = "taskassigning/taskAssigningSolverConfig.xml";

    @Override
    public boolean init() {
        logger.info("Initializing " + TaskAssigningRealTimePlanning.class.getName());
        return true;
    }

    @Override
    public void close() {
        logger.info("Closing " + TaskAssigningRealTimePlanning.class.getName());
    }

    @Override
    public String getSolverId() {
        return SOLVER_ID;
    }

    @Override
    public String getSolverConfigPath() {
        return SOLVER_CONFIG_PATH;
    }

    @Override
    public boolean accept(String containerId) {
        return true;
    }

    @Override
    public SolverEventListener<Object> getCallback() {
        return (solution) -> logger.debug("New best solution found: " + solution);
    }

    @Override
    public List<ProblemFactChange<Object>> loadFacts() {
        return new ArrayList<>(0);
    }

    @Override
    public Object getPlanningProblem() {
        TaskAssigningGenerator generator = new TaskAssigningGenerator();
        return generator.createTaskAssigningSolution("solution", 10, 3, 5, 3, 5);
    }
}
