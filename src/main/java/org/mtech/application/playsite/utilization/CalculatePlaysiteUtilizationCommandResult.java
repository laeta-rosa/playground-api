package org.mtech.application.playsite.utilization;

import org.mtech.domain.vo.PlaysiteId;
import org.mtech.domain.vo.PlaysiteUtilization;

public sealed interface CalculatePlaysiteUtilizationCommandResult {

    record UtilizationCalculated(PlaysiteUtilization utilization) implements CalculatePlaysiteUtilizationCommandResult {}

    record PlaysiteToCalculateUtilizationNotFound(PlaysiteId id) implements CalculatePlaysiteUtilizationCommandResult {}

}
