package com.froi.discounts.opinion.infrastructure.outputports.db;

import com.froi.discounts.opinion.domain.Opinion;

public interface MakeOpinionOutputPort {
    Opinion makeRoomOpinion(Opinion opinion);
    Opinion makeDishOpinion(Opinion opinion);
}
