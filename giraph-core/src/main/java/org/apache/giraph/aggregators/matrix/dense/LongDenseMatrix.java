/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.giraph.aggregators.matrix.dense;

import java.util.ArrayList;

/**
 * A long matrix holds the values of the entries in long vectors. It keeps one
 * long aggregator per matrix row.
 */
public class LongDenseMatrix {
  /** The number of rows in the matrix */
  private int numRows;
  /** The number of columns in the matrix */
  private int numColumns;
  /** The rows of the matrix */
  private ArrayList<LongDenseVector> rows = null;

  /**
   * Create a new matrix with the same number of rows and columns.
   *
   * @param size the number of rows and columns
   */
  public LongDenseMatrix(int size) {
    this(size, size);
  }

  /**
   * Create a new matrix with the given number of rows and columns.
   *
   * @param numRows the number of rows
   * @param numColumns the number of columns
   */
  public LongDenseMatrix(int numRows, int numColumns) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    rows = new ArrayList<LongDenseVector>();
  }

  /**
   * Create a empty matrix with all values set to 0.0
   */
  public void initialize() {
    rows.clear();
    for (int i = 0; i < numRows; ++i) {
      rows.add(new LongDenseVector(numColumns));
    }
  }

  /**
   * Get the number of rows in the matrix.
   *
   * @return the number of rows
   */
  public int getNumRows() {
    return numRows;
  }

  /**
   * Get the number of the columns in the matrix.
   *
   * @return the number of rows
   */
  public int getNumColumns() {
    return numColumns;
  }

  /**
   * Get a specific entry of the matrix.
   *
   * @param i the row
   * @param j the column
   * @return the value of the entry
   */
  public long get(int i, int j) {
    return rows.get(i).get(j);
  }

  /**
   * Set a specific entry of the matrix.
   *
   * @param i the row
   * @param j the column
   * @param v the value of the entry
   */
  public void set(int i, int j, long v) {
    rows.get(i).set(j, v);
  }

  /**
   * Get a specific row of the matrix.
   *
   * @param i the row number
   * @return the row of the matrix
   */
  LongDenseVector getRow(int i) {
    return rows.get(i);
  }

  /**
   * Add the long vector as a row in the matrix.
   *
   * @param vec the vector to add
   */
  void addRow(LongDenseVector vec) {
    if (rows.size() >= numRows) {
      throw new RuntimeException("Cannot add more rows!");
    }
    rows.add(vec);
  }
}
