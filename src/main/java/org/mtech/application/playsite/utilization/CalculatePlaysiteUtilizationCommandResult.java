package org.mtech.application.playsite.utilization;

import org.mtech.domain.vo.PlaysiteUtilization;

public sealed interface CalculatePlaysiteUtilizationCommandResult {

    record UtilizationCalculated(PlaysiteUtilization utilization) implements CalculatePlaysiteUtilizationCommandResult {}

    record PlaysiteToCalculateUtilizationNotFound(Long id) implements CalculatePlaysiteUtilizationCommandResult {}

}
