package com.mazecube.myapplication.binding

/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mazecube.myapplication.ui.common.DataBoundListAdapter
import com.skydoves.whatif.whatIfNotNullAs

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<DataBoundListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("submitListPager")
    fun bindSubmitListPager(view: ViewPager2, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<DataBoundListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }

//        (dataBoundListAdapter as? DataBoundListAdapter<Any, *>)?.let { dataBoundListAdapter ->
//        }
    }
}
