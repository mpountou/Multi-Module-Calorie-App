package com.intelligent.feature_tracker_ui.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.intelligent.feature_tracker_domain.models.TrackedFood
import com.intelligent.common.R
import com.intelligent.feature_tracker_domain.models.MealType
import com.intelligent.ui.LocalSpacing
import com.intelligent.ui.components.NutrientInfo
import java.time.LocalDate

@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(5.dp))
            .shadow(
                elevation = spacing.medium,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(spacing.extraSmall)
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(5.dp)
                ),
            painter = rememberImagePainter(
                data = trackedFood.imageUrl,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                text = stringResource(
                    id = R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                )
            )
        }
        Column {
            Icon(
                modifier = Modifier.align(End)
                    .clickable { onDeleteClick() },
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
            Row {
                NutrientInfo(
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    subTitle = stringResource(id = R.string.fat)
                )
                Spacer(modifier = Modifier.width(spacing.medium))
                NutrientInfo(
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    subTitle = stringResource(id = R.string.fat)
                )
                Spacer(modifier = Modifier.width(spacing.medium))
                NutrientInfo(
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    subTitle = stringResource(id = R.string.fat)
                )
            }
        }
    }
}

@Preview
@Composable
fun TrackedFoodItemPreview() {
    TrackedFoodItem(
        trackedFood = TrackedFood(
            "Pumpin Ginger and Rice Noodles", 2, 2, 2, "", MealType.Breakfast, 1, LocalDate.now(), 1, 1
        ), onDeleteClick = { /*TODO*/ }
    )
}