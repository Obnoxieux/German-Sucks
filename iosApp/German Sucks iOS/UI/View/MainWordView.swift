//
//  MainWordView.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct MainWordView: View {
    @StateObject var mainWordViewModel = MainWordViewModel()
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15) {
            ExplanationCard()
            MainWordCard(currentWord: mainWordViewModel.currentWord)
            ShareCard()
            Spacer()
            HStack {
                Spacer()
                Button(action: {print("tapped")}) {
                    Label("Load your new German word here", systemImage: "sparkles")
                }
                .buttonStyle(.borderedProminent)
                .controlSize(.large)
                .padding(.bottom)
                Spacer()
            }
        }
        .padding()
        .navigationTitle("German Sucks")
    }
}

extension MainWordView {
    class MainWordViewModel: ObservableObject {
        @Published var currentWord = "No word loaded"
        let wordProvider = WordProviderPlatform()
        
    }
}

struct MainWordView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            MainWordView()
        }
    }
}
