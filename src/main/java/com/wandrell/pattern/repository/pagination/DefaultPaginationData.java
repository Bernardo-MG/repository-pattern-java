/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014-2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.pattern.repository.pagination;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@code PaginationData}.
 * <p>
 * This is an immutable class, which does no allow changed the contained data.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class DefaultPaginationData implements PaginationData {

    /**
     * Number of the page.
     */
    private final Integer pageNumber;

    /**
     * Size of the page.
     * <p>
     * This is the number of entries, entities or similar in the page.
     */
    private final Integer pageSize;

    /**
     * Constructs a {@code DefaultPaginationData} with the specified parameters.
     * 
     * @param size
     *            size of the page
     * @param page
     *            number of the page
     */
    public DefaultPaginationData(final Integer size, final Integer page) {
        super();

        pageSize = checkNotNull(size, "Received a null pointer as page size");
        pageNumber = checkNotNull(page,
                "Received a null pointer as page number");
    }

    @Override
    public final Integer getPageNumber() {
        return pageNumber;
    }

    @Override
    public final Integer getPageSize() {
        return pageSize;
    }

}
