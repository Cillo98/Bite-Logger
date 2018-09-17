package thomascilloni.xyz.bitelogger.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import thomascilloni.xyz.bitelogger.R


class NutritionChart (context: Context, attrs: AttributeSet, private var mNutritionData: FloatArray)
    : View(context, attrs) {

    private val TAG = "NutritionChart"

    // CONSTRUCTOR for layout editors
    constructor(context: Context, attrs: AttributeSet):
            this(context, attrs, floatArrayOf(1f, 1f, 1f))

    // PAINT objects
    private var arcsPaint = Paint()
    private var centerPaint = Paint()
    private var textPaint = Paint()

    // varying parameters for drawings
    private var angle = 0f
    private var center = 0f
    private val rectF = RectF()
    private var w = width.toFloat()

    // ATTRIBUTES with default values
    private var showText = true
    private var textSize = 40
    private var barWidth = 30
    private lateinit var colors: IntArray

    // INTERNAL DATA
    private var firstRun = true
    private var scaledValues: FloatArray
    private var totalCalories = 0f


    /**
     * Get XML properties.
     * Get all the properties given in the XML file
     * where this view is defined. If values are not present,
     * default are given instead.
     */
    init {
        // Get all the XML attributes for this object
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.NutritionChart,
                0, 0).apply {

            try {
                showText = getBoolean(R.styleable.NutritionChart_showText, true)
                textSize = getInteger(R.styleable.NutritionChart_textSize, 40)
                barWidth = getInteger(R.styleable.NutritionChart_barWidth, 30)
                colors = intArrayOf(
                        getInteger(R.styleable.NutritionChart_carbohydrates_color, Color.YELLOW),
                        getInteger(R.styleable.NutritionChart_proteins_color, Color.GREEN),
                        getInteger(R.styleable.NutritionChart_fats_color, Color.RED))
            } finally {
                recycle()
            }
        }
    }

    /**
     *  Setup all instance variables.
     *  This includes the initialization of all
     *  Paint objects with specific characteristics
     *  (stroke width, style etc...)
     *
      */
    init {
        isFocusable = true

        // Arcs
        arcsPaint.isAntiAlias = true
        arcsPaint.color = Color.RED
        arcsPaint.style = Paint.Style.STROKE
        arcsPaint.strokeWidth = 0f
        arcsPaint.style = Paint.Style.FILL

        // Central circle
        centerPaint.style = Paint.Style.FILL
        centerPaint.color = ResourcesCompat.getColor(resources, R.color.background_material_light, null)

        // Text
        textPaint.style = Paint.Style.FILL
        textPaint.strokeWidth = 2f
        textPaint.color = Color.MAGENTA
        textPaint.textSize = 60f
        textPaint.textAlign = Paint.Align.CENTER

        // Arcs data
        scaledValues = getArcValues()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        updateValues()

        drawArcs(canvas)
        drawCentralCircle(canvas)
        drawText(canvas)
    }

    private fun updateValues() {
        // update this object's width in case the dimensions have updated
        w = width.toFloat()

        // in the first run, calculate the values for the arcs
        if (firstRun) {
            scaledValues = getArcValues()
            firstRun = false
        }

        // update the size of the rectangle with the size of the view
        rectF.set(0f, 0f, w, w)
    }
    private fun drawArcs(canvas: Canvas) {
        // draw the arcs
        for (i in 0..2) {
            arcsPaint.color = colors[i]

            canvas.drawArc(rectF, angle, scaledValues[i], true, arcsPaint)
            angle += scaledValues[i]
        }
    }
    private fun drawCentralCircle(canvas: Canvas) {
        // the radius depends on the width of the bar. The width of the bar
        // is given as percentage of the view's width. The radius is half of
        // the view's width minus the bar, which is a percentage of the width
        val barWidth = 30f

        val radius = width * (1 - barWidth/100) / 2
        center = width/2f
        canvas.drawCircle(center, center, radius, centerPaint)
    }
    private fun drawText(canvas: Canvas) {
        // vertical repositioning to make the text centered, instead of
        // letting the text's baseline be centered
        val yMov = ((textPaint.descent() + textPaint.ascent()) / 2)

        canvas.drawText(totalCalories.toInt().toString(),
                w/2, w/2 - yMov, textPaint)
    }

    /**
     * Set the macronutrients of this object.
     * The array must have length of 3 and contain the values
     * in grams for each of the 3 macronutrients of the food:
     * carbohydrates, proteins and fats, in this order.
     *
     * @param data the array containing the grams macronutrients
     */
    fun setData(data: FloatArray) {
        mNutritionData = data
        invalidate()
        Log.d(TAG, "Data reset and view invalidated")
    }

    /**
     *  Calculate the portion of arc for each macronutrient
     */
    private fun getArcValues(): FloatArray {
        // calculate the portions of pie to draw
        val portions = FloatArray(3)
        totalCalories = calculateCalories(mNutritionData) //Total all values supplied to the chart

        portions[0] = mNutritionData[0]*4 / totalCalories * 360f
        portions[1] = mNutritionData[1]*4 / totalCalories * 360f
        portions[2] = mNutritionData[2]*9 / totalCalories * 360f

        Log.d(TAG, "the getArcValues are ${portions[0]}, ${portions[1]}, ${portions[2]}")
        return portions
    }

    /**
     * Calculate the total calories given macronutrients
     */
    private fun calculateCalories(nData: FloatArray): Float {
        if (nData.size != 3) //|| nData2?.size != 3)
            throw IndexOutOfBoundsException("Array with nutrition data " +
                    "not the right size. Required: 3, Found: ${nData.size}")

        return (nData[0]*4 + nData[1]*4 + nData[2]*9)
    }
}