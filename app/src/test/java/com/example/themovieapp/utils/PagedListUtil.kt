package com.example.themovieapp.utils

import java.nio.file.Files.size
import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.stubbing.Answer
import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.invocation.Invocation


class PagedListUtil {

    companion object {
        fun <T> mockPagedList(list: List<T>): PagedList<T> {
            val pagedList = mock(PagedList::class.java) as PagedList<T>
            `when`(pagedList[anyInt()]).then { invocation ->
                val index = invocation.arguments.first() as Int
                list[index]
            }
            `when`(pagedList.size).thenReturn(list.size)
            return pagedList
        }
    }
}