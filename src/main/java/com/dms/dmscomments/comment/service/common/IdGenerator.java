package com.dms.dmscomments.comment.service.common;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import java.util.UUID;

public class IdGenerator {
    private static final TimeBasedEpochRandomGenerator timeBasedGenerator = Generators.timeBasedEpochRandomGenerator();

    private IdGenerator() {
    }

    public static UUID generateTimeBaseUUID() {
        return timeBasedGenerator.generate();
    }
}
