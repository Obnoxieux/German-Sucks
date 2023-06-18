//
//  MainWordView.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct MainWordView: View {
    @StateObject var vm = MainWordViewModel()
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15) {
            ExplanationCard()
            MainWordCard(vm: vm)
            ShareCard()
            Spacer()
            HStack {
                Spacer()
                Button(action: {
                    vm.setCurrentWord()
                }) {
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
        let shareLookupDataProvider = ShareLookupDataProvider()
        
        func setCurrentWord() {
            currentWord = wordProvider.getRandomWord()
        }
        
        func lookupWordOnline(service: SharingService) -> String {
            return shareLookupDataProvider.getLookupURL(service: service, searchTerm: currentWord)
        }
    }
}

struct MainWordView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            MainWordView()
        }
    }
}
