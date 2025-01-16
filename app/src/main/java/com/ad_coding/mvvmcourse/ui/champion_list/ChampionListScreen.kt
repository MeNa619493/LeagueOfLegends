package com.ad_coding.mvvmcourse.ui.champion_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChampionListScreen(
    state: ChampionListState,
    onSearchTextChange: (String) -> Unit,
    navigate: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = state.searchText,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                placeholder = {
                    Text(text = "Search for champs")
                }
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(state.filteredChampions.ifEmpty { state.champions }) {
                    ChampionCard(
                        champion = it,
                        modifier = Modifier
                            .animateItem()
                            .clickable {
                                it.name?.let(navigate)
                            }
                    )
                }
            }
        }
    }
}