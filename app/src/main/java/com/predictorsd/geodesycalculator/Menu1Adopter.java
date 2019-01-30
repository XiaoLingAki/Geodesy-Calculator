package com.predictorsd.geodesycalculator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Menu1Adopter extends RecyclerView.Adapter<Menu1Adopter.ViewHolder> {
    //数据列表
    //列表中的每一项是一个包含控件对应资源的对象
    //每一项滚动至屏幕内时调用该类中方法进行绑定
    private List<Actions> actionsList;

    //根据布局的不同，指定不同的ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{

        View rview;
        ImageView imageView;
        TextView textView;
        String launchAction;

        ViewHolder(View view){
            super(view);
            rview=view;
            imageView=view.findViewById(R.id.action_image);
            textView=view.findViewById(R.id.action_name);
        }
    }

    Menu1Adopter(List<Actions> list){actionsList=list;}
    //重载方法，读取布局文件，存入ViewHolder
    @NonNull
    @Override
    public Menu1Adopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_1_items,parent,false);

        //控件和子控件按键响应
        final Menu1Adopter.ViewHolder holder = new Menu1Adopter.ViewHolder(view);
        holder.rview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),holder.textView.getText(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(holder.launchAction);
                v.getContext().startActivity(intent);
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.predictorsd.TGLAUNCH");
                v.getContext().sendBroadcast(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actions actions=actionsList.get(position);
        holder.imageView.setImageResource(actions.getImageID());
        holder.textView.setText(actions.getName());
        holder.launchAction=actions.getLstring();
    }


    //重载方法，获取列表项个数
    @Override
    public int getItemCount() {
        return actionsList.size();
    }
}
