package com.biz.memo.adaper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.biz.memo.domain.MemoVO
import com.biz.memo.repository.MemoRepository

/*
DB와 연동하여 화면에 데이터를 보일때
직접 DB로 부터 데이터를 가져와서 보이지 않고
또하나의 중간 매개체를 통해서 처리를 수행하기 위해 사용하는 클래스로서
안드로이드에서는 이 클래스를 ViewModel 이라고 한다.
*/
class MemoViewModel(application: Application) : AndroidViewModel(application) {

//    private val application : Application = getApplication()
    private var memoRepository: MemoRepository = MemoRepository(application)
    private var memoList: LiveData<MutableList<MemoVO?>?>?

//    constructor(application: Application) : super(application){
//        this.application = application
//        memoRepository = MemoRepository(application)

///    }

    fun selectAll(): LiveData<MutableList<MemoVO?>?>? {
        return memoRepository.selectAll()
    }

    fun insert(memoVO: MemoVO) {
        memoRepository.insert(memoVO)
    }

    fun delete(id : Long) {

        memoRepository.delete(id)
    }

    init {
        memoList = memoRepository.selectAll()
    }
}