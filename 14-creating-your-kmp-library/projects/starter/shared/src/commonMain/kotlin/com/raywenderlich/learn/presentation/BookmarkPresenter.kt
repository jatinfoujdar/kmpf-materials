/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.learn.presentation

import com.raywenderlich.learn.PresenterCoroutineScope
import com.raywenderlich.learn.data.model.RWEntry
import com.raywenderlich.learn.domain.cb.BookmarkData
import com.raywenderlich.learn.domain.dao.RWEntryDAO
import com.raywenderlich.learn.domain.defaultDispatcher
import com.raywenderlich.learn.platform.Logger
import kotlinx.coroutines.launch

private const val TAG = "BookmarkPresenter"

class BookmarkPresenter(private val rwEntryDAO: RWEntryDAO) {

  private val scope = PresenterCoroutineScope(defaultDispatcher)
  private var listener: BookmarkData? = null

  public fun getBookmarks(cb: BookmarkData) {
    Logger.d(TAG, "getBookmarks")
    listener = cb
    getBookmarks()
  }

  private fun getBookmarks() {
    scope.launch {
      val bookmarks = rwEntryDAO.getAllEntries().filter { it.bookmarked }
      listener?.onNewBookmarksList(bookmarks)
    }
  }

  public fun addAsBookmark(entry: RWEntry, cb: BookmarkData) {
    Logger.d(TAG, "addAsBookmark")
    listener = cb
    addAsBookmark(entry.copy(bookmarked = true))
  }

  private fun addAsBookmark(entry: RWEntry) {
    scope.launch {
      rwEntryDAO.insertOrReplace(entry)
      listener?.onBookmarkStateUpdated(entry, true)
    }
  }

  public fun removeFromBookmark(entry: RWEntry, cb: BookmarkData) {
    Logger.d(TAG, "removeFromBookmark")
    listener = cb
    removeFromBookmark(entry.copy(bookmarked = false))
  }

  private fun removeFromBookmark(entry: RWEntry) {
    scope.launch {
      rwEntryDAO.remove(entry)
      listener?.onBookmarkStateUpdated(entry, true)
    }
  }
}