package sample.nackun.com.studyfirst.presentation.util

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import io.reactivex.subjects.PublishSubject
import sample.nackun.com.studyfirst.presentation.base.BaseRecyclerView

open class ClickAdapter<ITEM : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    bindingVariabledId: Int
) : BaseRecyclerView.BaseAdapter<ITEM, B>(
    layoutRes,
    bindingVariabledId
) {
    private val onItemClickSubject = PublishSubject.create<Int>()

    fun getOnItemClickObservable() = onItemClickSubject

    override fun onBindViewHolder(baseViewHolder: BaseRecyclerView.BaseViewHolder<B>, position: Int) {
        items[position].let(baseViewHolder::bind)
        baseViewHolder.itemView.setOnClickListener { it ->
            onItemClickSubject.onNext(baseViewHolder.adapterPosition)
        }
    }
}