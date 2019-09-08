package com.kalfian.movieapp.services.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.kalfian.movieapp.R
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class MovieFavoriteWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (Objects.equals(intent?.action, AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            val appsId = intent?.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
            if (appsId != null) {
                for (i in 0 until appsId.size) {
                    val appWidgetManager = AppWidgetManager.getInstance(context)
                    appWidgetManager.notifyAppWidgetViewDataChanged(i, R.id.stack_view_favorite)
                }
            }
        }
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {

            val i = Intent(context, StackWidgetService::class.java)
            i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.movie_favorite_widget)
            views.setRemoteAdapter(R.id.stack_view_favorite, i)
            views.setEmptyView(R.id.stack_view_favorite, R.id.empty_view)



            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

