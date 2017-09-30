package hackathon.com.salemovedroid.views

import android.content.Context
import android.graphics.Color
import hackathon.com.salemovedroid.model.Operator
import hackathon.com.salemovedroid.views.base.BaseView
import hackathon.com.salemovedroid.views.base.CGRect

/**
 * Created by aareundo on 30/09/2017.
 */
class MainView(context: Context) : BaseView(context) {

    init {
        setBackgroundColor(Color.WHITE)

        setMainViewFrame()
    }

    val cells = mutableListOf<OperatorCell>()

    fun addOperators(operators: MutableList<Operator>) {

        for (i in 0..(operators.count() - 1)) {

            val operator = operators[i]
            val cell = OperatorCell(context)
            cell.update(operator)
            cells.add(cell)
            addView(cell)
        }

        val padding = (5 * getDensity()).toInt()

        val x = padding
        var y = padding
        val w = frame.width - 2 * padding
        val h = (75 * getDensity()).toInt()

        for (i in 0..(cells.count() - 1)) {
            cells[i].setFrame(x, -h, w, h)
            cells[i].trueY = y
            y += h + padding
        }

        animateFrames(0)
    }

    fun animateFrames(counter: Int) {

        if (counter == cells.count()) {
            return
        }

        val cell = cells[counter]

        cell.animateY(cell.trueY, {

            val next = counter + 1;
            animateFrames(next)

        })
    }


}