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

/**
 * Interface for pagination data.
 * <p>
 * This allows creating a page from a set of data. A page is a division of said
 * data, to avoid handling all of it at once.
 * <p>
 * For example, the entities in a repository can be paginated in sets of five
 * entities, which would allow acquiring just the third set of five, or what is
 * the same, the eleventh to fifteenth entities.
 * 
 * @author Bernardo Martínez Garrido
 * @see com.wandrell.pattern.repository.pagination.PaginatedRepository PaginatedRepository
 */
public interface PaginationData {

    /**
     * Returns the number of the page.
     * 
     * @return the number of the page
     */
    public Integer getPageNumber();

    /**
     * Returns the size of the page.
     * <p>
     * This is the number of entries, entities or similar which will be
     * contained in the page.
     * 
     * @return the size of the page
     */
    public Integer getPageSize();

}
