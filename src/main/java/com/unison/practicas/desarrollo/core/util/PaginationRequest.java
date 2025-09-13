package com.unison.practicas.desarrollo.core.util;

import java.util.List;

public record PaginationRequest(
        Integer page,
        Integer size,
        List<SortRequest> sorts
) {
}