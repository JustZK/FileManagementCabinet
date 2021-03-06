package com.zk.cabinet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.zk.cabinet.R
import com.zk.cabinet.bean.ResultGetInStorage
import com.zk.cabinet.constant.SelfComm

class WarehousingAdapter(
    context: Context,
    dossierList: ArrayList<ResultGetInStorage.NameValuePairsBeanX.DataBean.ValuesBean>
) :
    BaseAdapter() {
    private val mContext = context
    private var mDossierList = dossierList
    private val mLayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var view = convertView
        val dossier = mDossierList[position]
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.adapter_warehousing_item, null)
            viewHolder = ViewHolder(
                view.findViewById(R.id.adapter_warehousing_ll),
                view.findViewById(R.id.adapter_warehousing_number_tv),
                view.findViewById(R.id.adapter_warehousing_rfid_tv),
                view.findViewById(R.id.adapter_warehousing_name_tv),
                view.findViewById(R.id.adapter_warehousing_no_tv),
                view.findViewById(R.id.adapter_warehousing_cate_tv),
                view.findViewById(R.id.tv_warranType),
                view.findViewById(R.id.adapter_warehousing_type_tv),
                view.findViewById(R.id.adapter_outbound_position_tv)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mAdapterWarehousingNumberTv.text = dossier.nameValuePairs.warrantNum
        viewHolder.mAdapterWarehousingRfidTv.text = dossier.nameValuePairs.rfidNum
        viewHolder.mAdapterWarehousingNameTv.text = dossier.nameValuePairs.warrantName
        viewHolder.mAdapterWarehousingNoTv.text = dossier.nameValuePairs.warrantNo
        viewHolder.mAdapterWarehousingCateTv.text =
            SelfComm.WARRANT_CATE[dossier.nameValuePairs.warranCate]
        viewHolder.tv_warranType.text = SelfComm.WARRANT_TYPE[dossier.nameValuePairs.warranType]
        viewHolder.mAdapterWarehousingTypeTv.text =
            SelfComm.OPERATING_TYPE[dossier.nameValuePairs.inStorageType]

        viewHolder.mAdapterOutboundPositionTv.text =
            "${dossier.nameValuePairs.cabCode} - ${dossier.nameValuePairs.position}-${dossier.nameValuePairs.light}"

//        if (position % 2 == 0) {
////            viewHolder.mAdapterWarehousingLl.setBackgroundColor(
////                mContext.resources.getColor(R.color.md_blue_100)
////            )
////        } else {
////            viewHolder.mAdapterWarehousingLl.setBackgroundColor(
////                mContext.resources.getColor(R.color.md_blue_200)
////            )
////        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return mDossierList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mDossierList.size
    }

    private class ViewHolder(
        var mAdapterWarehousingLl: LinearLayout,
        var mAdapterWarehousingNumberTv: TextView,
        var mAdapterWarehousingRfidTv: TextView,
        var mAdapterWarehousingNameTv: TextView,
        var mAdapterWarehousingNoTv: TextView,
        var mAdapterWarehousingCateTv: TextView,
        var tv_warranType: TextView,
        var mAdapterWarehousingTypeTv: TextView,
        var mAdapterOutboundPositionTv: TextView
    )

    fun setList(dossierList: ArrayList<ResultGetInStorage.NameValuePairsBeanX.DataBean.ValuesBean>) {
        mDossierList = dossierList
    }
}